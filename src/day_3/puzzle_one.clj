(ns day-3.puzzle-one
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def item-priorities
  "Value representations for items in the rucksack."
  (letfn [(f [r1 r2] (zipmap (map char r1) r2))]
    (merge (f (range 97 124) (range 1 27))
           (f (range 65 91) (range 27 53)))))

(defn seperate-rucksacks [s]
  "Seperates the rucksacks."
  (str/split-lines s))

(defn compartmentalize [s]
  "Seperate rucksack items into their compartments."
  (map str/join (split-at (/ (count s) 2) s)))

(defn shared-item [xs]
  "Takes the item compartments
and finds the item that is shared between them."
  (first (apply set/intersection (map set xs))))

(defn reorganization [s]
  (let [xf (comp (map compartmentalize)
                 (map shared-item)
                 (map item-priorities))]
    (transduce xf + 0 (seperate-rucksacks s))))
