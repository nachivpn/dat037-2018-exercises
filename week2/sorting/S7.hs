{-# OPTIONS -Wall #-}

import Data.List ( nub, sort )
import Debug.Trace ( trace )

--------------------------------------------------------------------------------

-- | Splits a list of elements into two parts.
-- | Makes 3 iterations through the list.
-- | Also in slides from lecture 4.
-- |
-- | Time complexity: O(N)
splitInHalf3 :: [a] -> ([a], [a])
splitInHalf3 xs = (take n xs, drop n xs)
  where
  n = length xs `div` 2

--------------------------------------------------------------------------------

-- | Splits a list of elements into two parts.
-- | Makes 2 iterations through the list.
-- |
-- | Time complexity: O(N)
splitInHalf2 :: [a] -> ([a], [a])
splitInHalf2 xs = takedrop n xs
  where
  n = length xs `div` 2

takedrop :: Int -> [a] -> ([a], [a])
takedrop n xs | n <= 0 = ([],xs)
takedrop _ []          = ([],[])
takedrop n (x:xs)      =
  let (ts, ds) = takedrop (n-1) xs
  in (x : ts, ds)

--------------------------------------------------------------------------------

-- | Splits a list of elements into two parts.
-- | Makes 1 iteration through the list.
-- |
-- | Time complexity: O(N)
splitInHalf :: [a] -> ([a], [a])
splitInHalf [] = ([],[])
splitInHalf [x] = ([],[x])
splitInHalf (x1:x2:xs) =
  let (as,bs) = splitInHalf xs
  in (x1:as,x2:bs)

--------------------------------------------------------------------------------

testOK :: (Show a, Ord a) => [a] -> ([a] -> ([a], [a])) -> Bool
testOK xs f =
  -- function result must be a partition of the elements with two parts.
  -- size of the 1st part must be less than or equal to the 2nd part.
  let (as,bs) = f xs
      al = length as
      bl = length bs
  in trace ("trying "++show xs) $
      sort (nub (as++bs)) == xs   -- partition of the elements with two parts
      && al <= bl                    -- 1st part less than or equal to 2nd part
      && max al bl - min al bl <= 1  -- as equal in size as possible
      && (null xs || testOK (tail xs) f)  -- test with smaller size

main :: IO ()
main =
  print $
    all (testOK [1..20::Int])
      [splitInHalf,splitInHalf2,splitInHalf3]

--------------------------------------------------------------------------------