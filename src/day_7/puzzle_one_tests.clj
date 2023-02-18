(ns day-7.puzzle-one-tests
  (:require [day-7.puzzle-one :as sut]
            [clojure.test :refer [deftest is testing]]))

(def input "$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k")

(comment
  {:type :cd :input "/"}
  {:type :ls}
  {:type :dir :name "a"}
  {:type :file :name "b.txt" :size 14848514})

(deftest parse-text-to-command
  (testing "Valid change directory command."
    (is (= (sut/parse-command "$ cd /")
           {:type :cd :input "/"}))
    (is (= (sut/parse-command "$ cd /a")
           {:type :cd :input "/a"}))
    (is (= (sut/parse-command "$ cd /my-directory")
           {:type :cd :input "/my-directory"}))
    (is (= (sut/parse-command "$ cd /my_directory")
           {:type :cd :input "/my_directory"})))

  (testing "Invalid change directory command."
    (is (= (sut/parse-command "$ cd /my directory")
           nil))
    (is (= (sut/parse-command "$ cd ")
           nil))
    (is (= (sut/parse-command "$ cd")
           nil))
    (is (= (sut/parse-command "cd")
           nil))
    (is (= (sut/parse-command " cd")
           nil)))

  (testing "Parse valid ls command text."
    (is (= (sut/parse-command "$ ls") {:type :ls})))

  (testing "Parse valid dir command."
    (is (= (sut/parse-command "dir a") {:type :dir :name "a"}))
    (is (= (sut/parse-command "dir abc") {:type :dir :name "abc"}))
    (is (= (sut/parse-command "dir a-b-c") {:type :dir :name "a-b-c"}))
    (is (= (sut/parse-command "dir a-b-c1234") {:type :dir :name "a-b-c1234"})))

  (testing "Parse valid dir command."
    (is (= (sut/parse-command "dir") nil))))
