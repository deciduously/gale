(ns gale.subs
  (:require
   [re-frame.core :as re-frame]
   [dorothy.core :as dot]))

(re-frame/reg-sub
 ::input-str
 (fn [db]
   (:input-str db)))

(re-frame/reg-sub
 ::output-str
 (fn [db]
   (try
     (dot/dot (dot/graph (cljs.reader/read-string @(re-frame/subscribe [::input-str]))))
     (catch :default e
       (str "Invalid dot input: " e)))))
