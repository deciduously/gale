(ns gale.events
  (:require
   [re-frame.core :as re-frame]
   [gale.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
  ::update-str
  (fn-traced [db [_ new-str]]
    (assoc db :input-str new-str)))