(ns clojure-noob.core
  (:gen-class))

(defn -main
  "Practicing the concepts in https://www.braveclojure.com/do-things/"
  [& args]
  ; ==Chapter: Do Things==
  (do
    (println
      (str "hey it's time for "
        (if (= true true)
          "lunch"
          "dinner"))
    )
    (do
      (def my-bound-arr [
        "a"
        "b"
        4
        "d"
      ])
      (def my-bound-ratio 5/3)
      (def my-bound-decimal 6.789)
    )
    (println my-bound-arr my-bound-ratio my-bound-decimal)
    (println "i know my abcs" (concat my-bound-arr))
    (def my-weird-map
      {:first my-bound-arr
         :second 55
         :third "third value"
         :fourth {:fourth-a "fourth val is" :fourth-b "a silly little map"}}
    )
    (println "here's the fourth thing in the map:" 
      (get 
        my-weird-map    
        :fourth)
    )
    (println "here is the fourth-b thing in the map:"
      (get-in
        my-weird-map
        [:fourth :fourth-b]))
    (println "simply looking up the map without a get:"
      ({:basically-an-arg "hey maps are functions too!"} :basically-an-arg))
    (println "simply looking up the map with the keyword first:"
      (:little-keyword {:little-ekeyword "keywords can come first when getting"}))
    (println (get ["a" {:name "my name is slim shady"} "c"] 1))
    (println (vector "first-elt" "second-elt" "third-elt"))
    (println '(1 2 "3ee" 4)) ; lists are different from vectors: you can't call get on them and they are heterogeneous
    (println "you have to use nth (O(N)) to get from a list, like:" (nth '(:a "bee" {:c 2} :d) 2))
    (println "add to the beginning of a list:" (conj '(1 2 3) 4))
    (println "oh btw you can have sets and look up stuff in them:" (contains? (conj (hash-set 1 1 2 2) 3) 3))
    (println "for hash set literals, use #{}:" (get #{1 2 3 4} 4))
    (if (contains? (conj #{1 2 3} (* 2 2)) 4)
      (do
        (println "conj worked!")
        (println "and it contains the new thing!")
        )
      (println "conj didn't work"))
    )
    (println "fun with functions:" ((or + nil -) 1 2 3)) ; the "or" func returns the first truthy value
    (println (map inc [0 1 2 3])) ; "map" in the map-reduce sense. applies the given func, "inc", to an array
    
    ;; define functions with "defn" and the final expression is what gets returned
    (defn my-cool-function
      "hey this is a docstring i guess"
      [name other-arg]
      (str "whoa, hey, " name "! it's so nice that you gave me this " other-arg "... cool arg!"))
    (println (my-cool-function "prem" "sandwich"))
    (println (my-cool-function "neil young" (conj ["song 1" "song 2"] "song 3")))
    
    ;; arity is how many args a function takes
    (defn multi-arity-function
      "Takes either 0, 1, or 2 args. So it's either 0-arity, 1-arity, or 2-arity! It's multi-arity, Jerry!"
      ([first-arg second-arg]
        (str "2-arity:" second-arg first-arg))
      ([first-arg]
        (str first-arg))
      ([]
        (str "no args given")))
    (println (multi-arity-function "wow"))
    (defn variable-arity-function
      "the & means 'put the rest of these into a list' and needs to come last"
      [name & my-list-of-args]
      (str "these are the args, " name ": " my-list-of-args))
    (println (variable-arity-function "my good friend" "arg-1" "arg-2"))
    (defn destructuring-demo
      "destructuring is taking a data structure and converting it to named args"
      [{my-lat :latitude my-lon :longitude}]
      (str "my lat is " my-lat " and my lon is " my-lon))
    (println (destructuring-demo {:latitude 120.5 :longitude 0}))
    ; let's do an anonymous function
    (def value-pointing-to-function (fn [arg-1] (str "this time i got: " arg-1 "\n")))
    (println (map value-pointing-to-function ["cake" "gift" "candles"]))
    ; also use # to define an anonymous function, used when the function is tiny
    (println (map #(* % 2) [3 4 5])) ; the % symbol here is equivalent to %1, or "first arg"

    ; "let" allows for scoped bindings
    (def x 0)
    (println (let [x (let [x 1] x)] (inc x))) ; in this scope, x is 1, then gets "inc"d
    (println x) ; prints the original value

    (def all-parts (vector "a" "b" "c"))
    (println (let [[first & remaining] all-parts] remaining)) ; quick way to return all except the first

    ; looping using loop/recur
    (loop [iteration 2]
      (println (str "iter: " iteration))
      (let [max 3]
      (if (> iteration max)
        (do 
          (println "bye bye!")
          (println (str "it's been " max " iterations, so it's time for you to go!")))
        (recur (inc iteration)))))
    
    ; exercise 2
    (defn apply-hundred
      "takes a function and an arg and passes 100 and the arg to it"
      [f arg & others]
      (if (not-empty others)
        (println "why did you give me more than two arg? extra:" others))
      (f 100 arg))
    (println (apply-hundred + 8))
    (println (apply-hundred + 6 7))

    ; exercise 3
    (defn dec-maker
      "custom decrementer"
      [dec-by]
      #(- % dec-by))
    (def dec9 (dec-maker 9))
    (println (dec9 100))

    ; exercise 4
    (defn mapset
      "like map except it returns a set"
      [f coll]
      (into (set (map f coll))))
    (println (mapset inc [1 1 2 2]))

    ; ==Chapter: Core Functions in Depth==
    (def identities [
      {:alias "Batman" :real "Bruce Wayne"}
      {:alias "Spiderman" :real "Peter Parker"}
    ])
    (println (map :real identities)) ; apply ":real" to each element of "identities" as if it's a function
)

 