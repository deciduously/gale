(ns gale.views
  (:require
   [re-frame.core :as re-frame]
   [gale.events :as events]
   [gale.subs :as subs]
   ))

(defn input-panel []
  (let [input-str (re-frame/subscribe [::subs/input-str])]
    (fn []
      [:textarea {:value @input-str :on-change #(re-frame/dispatch [::events/update-str (-> % .-target .-value)])}])))

; this will get handled by an effect
(defn output-panel []
    [:div#output-svg])

(defn svg-string []
  (let [output-str (re-frame/subscribe [::subs/output-svg])]
    (fn []
      [:div @output-str])))

(defn main-panel []
  [:div
   [:h1 "Gale"]
   [input-panel]
   [svg-string]
   [output-panel]
   ])
