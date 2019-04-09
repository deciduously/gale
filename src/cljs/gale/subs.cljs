(ns gale.subs
  (:require
   [re-frame.core :as re-frame]
   [dorothy.core :as dot]
   [viz.core :as viz]))

(re-frame/reg-sub
 ::input-str
 (fn [db]
   (:input-str db)))

(re-frame/reg-sub
 ::output-svg
 (fn [_]
   (try
     (-> @(re-frame/subscribe [::input-str])
         (cljs.reader/read-string)
         (dot/graph)
         (dot/dot)
         (viz/image))
     (catch :default e
       (str "Invalid dot input: " e)))))
