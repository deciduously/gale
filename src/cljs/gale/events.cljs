(ns gale.events
  (:require
   [re-frame.core :as re-frame]
   [gale.db :as db]
   [gale.subs :as subs]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
   [dorothy.core :as dot]
   [viz.core :as viz]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-fx
 :update-svg
 (fn-traced [_]
            (-> js/document
                (.getElementById "output-svg")
                (.-innerHtml)
                (set! (-> (re-frame/subscribe [::subs/input-str])
                          (dot/graph)
                          (dot/dot)
                          (viz/image))))
            {}))

(re-frame/reg-event-fx
  ::update-str
  (fn-traced [_ [_ new-str]]
             {:db (assoc db :input-str new-str)
              :update-svg}))


