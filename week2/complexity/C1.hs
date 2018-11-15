{-# OPTIONS -Wall #-}

import Data.List ( sort )

--------------------------------------------------------------------------------

-- | Time complexity:
-- | Requires 2^N uses of (:), each takes O(1) time. so O(2^N) time together.
-- | Requires 2^N elements in left side of (++), so takes O(2^N) time.
-- | T(0) = 1
-- | T(N) = 2^N + T(NтИТ1) = 2^(N+1) тИТ 1
-- | T(N) is O(2^N)
-- |
-- | Returns a list of all sublists.
subsets :: [a] -> [[a]]
subsets [] = [[]]
subsets (x:xs) = [ x : s | s <- ss ] ++ ss
  where ss = subsets xs

--------------------------------------------------------------------------------

testOK :: Bool
testOK =
  sort (subsets [1..3::Int]) == [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
    &&
  length (subsets [1..7::Int]) == 2^(7 :: Int)

main :: IO ()
main = print testOK

--------------------------------------------------------------------------------