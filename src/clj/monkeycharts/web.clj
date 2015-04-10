(ns monkeycharts.web
  (:require [clojure.edn :as edn]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.util.response :refer [file-response]]
            [ring.middleware.edn :refer [wrap-edn-params]]
            [clojure.java.jdbc :as jdbc]))

(def db (-> "config-db.edn"
            slurp
            edn/read-string))

(defn query-stats-daily []
  (jdbc/query db ["SELECT * FROM stats_daily ORDER BY day"]))

(defn get-school-size []
  (map #(select-keys % [:yearly_school_size_req :yearly_school_size_enr :day])
       (query-stats-daily)))

(defroutes routes
  (GET "/query/school-size" [] (pr-str (get-school-size)))
  (route/files "/" {:root "resources/public"})
  (route/not-found "<h1>Page not found</h1>"))

(def handler
  (-> routes
      wrap-edn-params))

(comment

(require 'monkeycharts.web :reload-all) (in-ns 'monkeycharts.web) (use 'clojure.repl)
  )
