package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class PokerDealer implements Dealer {
    private static final int PLAYERS_COUNT = 2;

    private static final int PAIR = 14;
    private static final int TWO_PAIRS = 15;
    private static final int THREE_OF_KIND = 16;
    private static final int STRAIGHT = 17;
    private static final int FLUSH = 18;
    private static final int FULL_HOUSE = 19;
    private static final int FOUR_OF_KIND = 20;
    private static final int STRAIGHT_FLUSH = 21;
    private static final int ROYAL_FLUSH = 22;

    private final List<Card> deck = new ArrayList<>();
    private final List<Card> community = new ArrayList<>();
    private final Map<Integer, List<Card>> hands = new HashMap<>();

    private final Map<Integer, CombinationResult> resultsDetails = new HashMap<>();

    {
        for (int i = 0; i < PLAYERS_COUNT; i++) {
            hands.put(i, new ArrayList<>());
        }
    }

    public PokerDealer() {
        createDeck();
    }

    private void createDeck() {
        for (String suit : new String[]{"H", "D", "C", "S"}) {
            int grade = 0;
            for (String rank : new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}) {
                deck.add(new Card(suit, rank, grade++));
            }
        }
    }

    @Override
    public Board dealCardsToPlayers() {
        return new Board(
                dealCards(hands.get(0), 2),
                dealCards(hands.get(1), 2),
                null, null, null);
    }

    @Override
    public Board dealFlop(Board board) {
        return new Board(
                board.getPlayerOne(),
                board.getPlayerTwo(),
                dealCards(community, 3),
                null, null
        );
    }

    @Override
    public Board dealTurn(Board board) {
        return new Board(
                board.getPlayerOne(),
                board.getPlayerTwo(),
                board.getFlop(),
                dealCards(community, 1),
                null
        );
    }

    @Override
    public Board dealRiver(Board board) {
        return new Board(
                board.getPlayerOne(),
                board.getPlayerTwo(),
                board.getFlop(),
                board.getTurn(),
                dealCards(community, 1)
        );
    }

    @Override
    public PokerResult decideWinner(Board board) throws InvalidPokerBoardException {
        checkBoard();
        for (int i = 0; i < PLAYERS_COUNT; i++) {
            resultsDetails.put(i, getResult(hands.get(i)));
            System.out.println("Игрок " + (i + 1) + ": " + resultsDetails.get(i));
        }
        if (resultsDetails.get(0).score > resultsDetails.get(1).score) {
            return PokerResult.PLAYER_ONE_WIN;
        } else if (resultsDetails.get(0).score < resultsDetails.get(1).score) {
            return PokerResult.PLAYER_TWO_WIN;
        } else {
            return PokerResult.DRAW;
        }
    }

    private String dealCards(List<Card> cards, int count) {
        Random rnd = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            Card card = deck.remove(rnd.nextInt(deck.size()));
            cards.add(card);
            result.append(card);
        }
        return result.toString();
    }

    private CombinationResult getResult(List<Card> hand) {
        List<Card> cards = new ArrayList<>(hand);
        cards.addAll(community);
        List<List<Card>> combinations = createCombinations(cards);

        int resultScore = 0;
        List<Card> higherCombination = null;
        for (List<Card> combination : combinations) {
            int combinationScore = checkCombination(combination);
            if (combinationScore > resultScore) {
                resultScore = combinationScore;
                higherCombination = combination;
            }
        }
        return new CombinationResult(resultScore, higherCombination);
    }

    int checkCombination(List<Card> combination) {
        int combinationScore = 0;
        int flashStraightScore = 0;
        if (isStraight(combination)) {
            flashStraightScore = STRAIGHT;
        }
        if (isFlush(combination)) {
            if (flashStraightScore == STRAIGHT) {
                if (combination.get(0).grade == 10) {
                    return ROYAL_FLUSH;
                } else {
                    return STRAIGHT_FLUSH;
                }
            }
            flashStraightScore = FLUSH;
        }
        Map<Integer, Long> kinds = createKinds(combination);
        int kindScore = 0;
        for (Long count : kinds.values()) {
            if (count == 4) {
                return FOUR_OF_KIND;
            } else if (count == 3) {
                kindScore = THREE_OF_KIND;
            } else if (count == 2) {
                if (kindScore == THREE_OF_KIND) {
                    return FULL_HOUSE;
                } else if (kindScore == PAIR) {
                    return TWO_PAIRS;
                } else {
                    kindScore = PAIR;
                }
            }
        }
        combinationScore = Math.max(flashStraightScore, kindScore);
        if (combinationScore == 0) {
            combinationScore = combination.stream().reduce(1,
                    (grade, card) -> card.grade == 0 ? 13 : card.grade,
                    Math::max);
        }
        return combinationScore;
    }

    private Map<Integer, Long> createKinds(List<Card> cards) {
        return cards.stream().collect(
                Collectors.groupingBy(Card::getGrade, Collectors.counting()));
    }

    private boolean isFlush(List<Card> cards) {
        return cards.stream().collect(Collectors.groupingBy(Card::getSuit)).size() == 1;
    }

    private boolean isStraight(List<Card> cards) {
        int lastIndex = 4;
        if (cards.stream().collect(Collectors.groupingBy(Card::getGrade)).size() == 5) {
            int dif = cards.get(lastIndex).grade - cards.get(0).grade;
            return (dif == 5 || dif == -10);
        }
        return false;
    }

    private List<List<Card>> createCombinations(List<Card> cards) {
        int n = 7;
        int k = 5;
        int[] comb = new int[5 + 2];
        List<List<Card>> combinations = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            comb[i] = i;
        }
        comb[k] = n;
        comb[k + 1] = 0;
        combinations.add(createCombination(cards, comb));
        while (true) {
            int i = 0;
            while (comb[i] + 1 == comb[i + 1]) {
                comb[i] = i++;
            }
            if (i < k) {
                comb[i]++;
            } else {
                break;
            }
            List<Card> combination = createCombination(cards, comb);
            combinations.add(combination);
        }
        for (List<Card> combination : combinations) {
            Collections.sort(combination);
        }
        return combinations;
    }

    private List<Card> createCombination(List<Card> cards, int[] comb) {
        List<Card> result = new ArrayList<>();
        for (int i = 0; i < comb.length - 2; i++) {
            result.add(cards.get(comb[i]));
        }
        return result;
    }

    private void checkBoard() {
        List<Card> cards = new ArrayList<>();
        cards.addAll(deck);
        cards.addAll(community);
        cards.addAll(hands.get(0));
        cards.addAll(hands.get(1));
        if (cards.stream().collect(Collectors.groupingBy(Card::toString)).size() != 52) {
            throw new InvalidPokerBoardException("На столе дублирующиеся карты!");
        };
    }

    static class Card implements Comparable<Card> {
        private final String suit;
        private final String rank;
        private final int grade;

        Card(String suit, String rank, int grade) {
            this.suit = suit;
            this.rank = rank;
            this.grade = grade;
        }

        public String getSuit() {
            return suit;
        }

        public int getGrade() {
            return grade;
        }

        @Override
        public int compareTo(Card card) {
            return grade - card.grade;
        }

        @Override
        public String toString() {
            return suit + rank;
        }
    }

    static class CombinationResult {
        int score;
        List<Card> cards;

        public CombinationResult(int score, List<Card> cards) {
            this.score = score;
            this.cards = cards;
        }

        @Override
        public String toString() {
            return "Самая сильная комбинация: {" +
                    "score=" + score +
                    ", cards=" + cards +
                    '}';
        }
    }
}
