(ns day-1.core
  (:require [clojure.string :as str]))

(defn highest-total-calories [list]
  (->> (str/split list #"\n\n")
       (map #(str/split % #"\n"))
       (map (fn [xs] (map #(Integer/parseInt %) xs)))
       (mapv #(apply + %))
       (sort)
       (last)))
