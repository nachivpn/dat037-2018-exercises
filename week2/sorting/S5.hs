{-# OPTIONS -Wall #-}

import Data.List ( sort, nub )

--------------------------------------------------------------------------------

-- | Removes duplicates from a list.
-- | Time complexity: O(N)
removeDuplicates :: Ord a => [a] -> [a]
removeDuplicates _xs = r (sort _xs)
  where
  r [] = []
  r [x] = [x]
  r (x1:x2:xs)
    | x1 == x2  =      r (x2:xs)
    | otherwise = x1 : r (x2:xs)

--------------------------------------------------------------------------------

-- | Time complexity is O(N^N)
numbers :: Int -> [[Int]]
numbers _n = loop [] _n _n
  where
  loop acc 0 _ = [acc]
  loop acc m n = concat [ loop (i:acc) (m-1) n | i <- [1..n] ]

testOK :: Bool
testOK = all (\xs -> removeDuplicates xs == sort (nub xs)) (numbers 6)

main :: IO ()
main = print testOK

--------------------------------------------------------------------------------