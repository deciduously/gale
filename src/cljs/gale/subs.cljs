(ns gale.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::input-str
 (fn [db]
   (:input-str db)))
