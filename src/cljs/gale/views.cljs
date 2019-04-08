(ns gale.views
  (:require
   [re-frame.core :as re-frame]
   [gale.subs :as subs]
   ))

(defn main-panel []
  (let [input-str (re-frame/subscribe [::subs/input-str])]
    [:div
     [:h1 "Current str " @input-str]
     ]))
