(ns day-1.core
  (:require [clojure.string :as str]))

(defn highest-total-calories [calorie-list n]
  (->> (str/split calorie-list #"\n\n")
       (map #(map (fn [s] (Integer/parseInt s)) (str/split % #"\n")))
       (map #(apply + %))
       (sort >)
       (take n)
       (apply +)))
