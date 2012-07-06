(ns three.core
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(defroutes main-routes
           (GET "/" [] "<h1>Hello world</h1>")
           (route/resources "/")
           (route/not-found "Page not found"))

(def app
  (handler/site main-routes))

