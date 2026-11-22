// İzlenebilirlik - basit servis çalışanı (telefona yüklenebilir uygulama için).
// Uygulama dosyaları önbelleğe alınır; veriler her zaman canlı sunucudan gelir.
const SURUM = 'izl-v0.24.0';
const DOSYALAR = ['./', './index.html', './lib/supabase.js', './lib/qrcode.js', './lib/jsQR.js', './manifest.json', './simge-192.png', './simge-512.png', './img/kocak-logo.png', './img/kocak-baklava.jpg', './img/kocak-serit.jpg'];
self.addEventListener('install', e => { e.waitUntil(caches.open(SURUM).then(c => c.addAll(DOSYALAR)).then(() => self.skipWaiting())); });
self.addEventListener('activate', e => {
  e.waitUntil(caches.keys().then(k => Promise.all(k.filter(x => x !== SURUM).map(x => caches.delete(x)))).then(() => self.clients.claim()));
});
self.addEventListener('fetch', e => {
  const u = new URL(e.request.url);
  if (e.request.method !== 'GET' || u.origin !== location.origin) return; // Supabase istekleri doğrudan sunucuya
  // Önce ağ (güncel sürüm), ağ yoksa önbellek
  e.respondWith(fetch(e.request).then(r => { const k = r.clone(); caches.open(SURUM).then(c => c.put(e.request, k)); return r; })
    .catch(() => caches.match(e.request)));
});
