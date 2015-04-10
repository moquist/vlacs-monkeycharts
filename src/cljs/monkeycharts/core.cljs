(ns ^:figwheel-always
  monkeycharts.core
    (:require[om.core :as om :include-macros true]
              [om.dom :as dom :include-macros true]
              [monkeycharts.utils :as utils]))

(enable-console-print!)

(defonce app-state (atom {:school-size []}))

(defn chart-school-size [school-size _owner]
  (reify
    om/IRender
    (render [_]
      (apply dom/div nil
             (map (comp #(dom/p nil %)
                        #(apply str %)
                        #(interpose " " %)
                        vals)
                  school-size)))))

(defn load-it-up []
  (utils/edn-xhr
   {:method :get
    :url "/query/school-size"
    :on-error (fn [err] (println err))
    :on-complete (fn [res] (om/update! (om/root-cursor app-state) :school-size res))}))

(om/root
  (fn [app owner]
    (reify om/IRender
      (render [_]
        (om/build chart-school-size (:school-size app)))))
  app-state
  {:target (. js/document (getElementById "app"))})


