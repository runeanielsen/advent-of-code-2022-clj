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

(defn paragraph-lines [s]
  (str/split-lines s))

(defn parse-paragraph-line [s]
  (let [l (str/split s #" ")]
    [(shape-conversion (first l))
     (encrypted-shape-conversion (second l))]))

(defn shape-matchup-score [[x y]]
  (condp = x
    :rock
    (condp = y
      :rock 3
      :paper 6
      :scissors 0)
    :paper
    (condp = y
      :rock 0
      :paper 3
      :scissors 6)
    :scissors
    (condp = y
      :rock 6
      :paper 0
      :scissors 3)))

(defn round-score [[x y :as t]]
  (+ (shape-matchup-score t) (shape-score y)))

(defn total-score [s]
  (->> (paragraph-lines s)
       (transduce (comp (map parse-paragraph-line)
                        (map round-score))
                  +
                  0)))
