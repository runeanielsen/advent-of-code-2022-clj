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

(defn match-point [[x y]]
  (condp = x
    :rock (condp = y
            :rock 3
            :paper 6
            :scissors 0)
    :paper (condp = y
             :rock 0
             :paper 3
             :scissors 6)
    :scissors (condp = y
                :rock 6
                :paper 0
                :scissors 3)))

(defn turn-points [[x y :as t]]
  (+ (match-point t) (shape-score y)))

(defn rock-paper-scissors [s]
  (->> (paragraph-lines s)
       (transduce (comp (map parse-paragraph-line)
                        (map turn-points))
                  +
                  0)))
