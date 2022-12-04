(ns day-1.tests
  (:require [day-1.core :as sut]
            [clojure.test :refer [deftest is testing]]
            [clojure.java.io :as io]))

(deftest highest-total-calories-returns-highest-total
  ;; First star question
  (testing "Getting highest elf output."
    (let [input (slurp (io/resource "day_1/calorie-list.txt"))]
      (is (= (sut/highest-total-calories input 1) 71502))))

  ;; Second star question
  (testing "Getting top three elf output."
    (let [input (slurp (io/resource "day_1/calorie-list.txt"))]
      (is (= (sut/highest-total-calories input 3) 208191)))))
