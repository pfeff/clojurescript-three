(ns three.dev-server
  (:use [ring.middleware.params :only (wrap-params)]
     [ring.middleware.file-info :only (wrap-file-info)]
     [ring.middleware.file :only (wrap-file)])
    (:require [three.reload :as reload])
    (:import java.io.File))

(defn- js-encoding [handler]
  (fn [request]
    (let [{:keys [headers body] :as response} (handler request)]
        (if (and (= (get headers "Content-Type") "text/javascript")
                 (= (type body) File))
            (assoc-in response [:headers "Content-Type"]
                   "text/javascript; charset=utf-8")
            response))))



(def app (-> app-routes
                     (reload/watch-cljs config)
                     (wrap-file "public")
                     ;rewrite-design-uris
                     wrap-file-info
                     ;apply-templates
                     js-encoding
                     wrap-params
                     ;set-active-menu
                     wrap-stacktrace
                     (reload/reload-clj (:reload-clj config))))

(defn run-server []
  (run-jetty (var app) {:join? false :port 8080}))

