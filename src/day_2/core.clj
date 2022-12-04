(ns day-2.core
  (:require [clojure.string :as str]))

(def shape-score
  {:rock 1
   :paper 2
   :scissors 3})

(def encrypted-shape-conversion
  {"X" :rock
   "Y" :paper
   "Z" :scissors})

(def shape-conversion
  {"A" :rock
   "B" :paper
   "C" :scissors})

(def matchup-score
  {:rock
   {:rock 3
    :paper 6
    :scissors 0}
   :paper
   {:rock 0
    :paper 3
    :scissors 6}
   :scissors
   {:rock 6
    :paper 0
    :scissors 3}})

(defn paragraph-lines [s]
  (str/split-lines s))

(defn parse-paragraph-line [s]
  (let [[x y] (str/split s #" ")]
    [(shape-conversion x)
     (encrypted-shape-conversion y)]))

(defn round-score [[x y :as t]]
  (+ (get-in matchup-score t) (shape-score y)))

(defn total-score [s]
  (->> (paragraph-lines s)
       (transduce (comp (map parse-paragraph-line)
                        (map round-score))
                  +
                  0)))
