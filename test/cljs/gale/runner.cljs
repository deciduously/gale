(ns gale.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [gale.core-test]))

(doo-tests 'gale.core-test)
