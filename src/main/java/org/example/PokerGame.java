package org.example;

public class PokerGame {
    public static void main(String[] args) {
        Dealer dealer = new PokerDealer();

        Board board = dealer.dealCardsToPlayers();
        board = dealer.dealFlop(board);
        board = dealer.dealTurn(board);
        board = dealer.dealRiver(board);

        PokerResult result = dealer.decideWinner(board);

        System.out.println(board.toString());
        System.out.println(result == PokerResult.DRAW ? "Ничья" : "Победитель: Игрок " + result);

//        System.out.println("Самая сильная комбинация Игрока 1: " + dealer.);
    }
}
