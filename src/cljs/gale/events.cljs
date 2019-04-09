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

(re-frame/reg-event-fx
  ::update-str
  (fn-traced [cofx [_ new-str]]
             {:db (assoc (:db cofx) :input-str new-str)
              :update-svg '()}))

(re-frame/reg-fx
 :update-svg
 (fn-traced [_]
            (let [g-svg (try (-> @(re-frame/subscribe [::subs/input-str])
                              (cljs.reader/read-string)
                              (dot/graph)
                              (dot/dot)
                              (viz/image))
                             (catch js/Error e
                               ("ERROR " e))]
                  (set! (.-innerHtml (.querySelector js/document "#output-svg")) g-svg)))))
