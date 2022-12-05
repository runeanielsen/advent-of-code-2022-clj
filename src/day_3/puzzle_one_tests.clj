(ns day-3.puzzle-one-tests
  (:require [day-3.puzzle-one :as sut]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is are testing]]))

;; Handle number representations for items in the rucksack.
(deftest rucksack-item-values-are-correct
  (are [x y] (= x y)
    16 (sut/item-priorities \p)
    38 (sut/item-priorities \L)
    42 (sut/item-priorities \P)
    22 (sut/item-priorities \v)
    20 (sut/item-priorities \t)
    19 (sut/item-priorities \s)))

;; Split the rucksack items in the two compartments.
(deftest split-items-in-compartments
  (are [x y] (= x y)
    ["vJrwpWtwJgWr" "hcsFMMfFFhFp"] (sut/compartmentalize "vJrwpWtwJgWrhcsFMMfFFhFp")
    ["jqHRNqRjqzjGDLGL" "rsFMfFZSrLrFZsSL"] (sut/compartmentalize "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")
    ["PmmdzqPrV" "vPwwTWBwg"] (sut/compartmentalize "PmmdzqPrVvPwwTWBwg")

    ["wMqvLMZHhHMvwLH" "jbvcjnnSBnvTQFn"] (sut/compartmentalize "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn")
    ["ttgJtRGJ" "QctTZtZT"] (sut/compartmentalize "ttgJtRGJQctTZtZT")
    ["CrZsJsPPZsGz" "wwsLwLmpwMDw"] (sut/compartmentalize "CrZsJsPPZsGzwwsLwLmpwMDw")))

;; Find shared items in rucksack compartments
(deftest find-shared-item-in-rucksack-compartments
  (are [x y] (= x y)
    \p (sut/shared-item ["vJrwpWtwJgWr" "hcsFMMfFFhFp"])
    \L (sut/shared-item ["jqHRNqRjqzjGDLGL" "rsFMfFZSrLrFZsSL"])
    \P (sut/shared-item ["PmmdzqPrV" "vPwwTWBwg"])
    \v (sut/shared-item ["wMqvLMZHhHMvwLH" "jbvcjnnSBnvTQFn"])
    \t (sut/shared-item ["ttgJtRGJ" "QctTZtZT"])
    \s (sut/shared-item ["CrZsJsPPZsGz" "wwsLwLmpwMDw"])))

;; Split the list of rucksacks
(deftest can-seperate-rucksacks
  (let [rucksacks "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"]
    (is ["vJrwpWtwJgWrhcsFMMfFFhFp"
         "PmmdzqPrVvPwwTWBwg"
         "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
         "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
         "ttgJtRGJQctTZtZT"
         "CrZsJsPPZsGzwwsLwLmpwMDw"]
        (sut/seperate-rucksacks rucksacks))))

(deftest reorganization-output-correct-total-priorities
  (testing "Example input."
    (let [rucksacks "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"]
      (is (= 157 (sut/reorganization rucksacks)))))

  (testing "User specific input."
    (let [rucksacks (slurp (io/resource "day_3/input.txt"))]
      (is (= 8105 (sut/reorganization rucksacks))))))
