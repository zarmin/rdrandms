package com.zarmin.rdrandms;

import net.nullschool.util.DigitalRandom;
import ratpack.jackson.Jackson;
import ratpack.jackson.JsonRender;
import ratpack.server.RatpackServer;

import java.util.Base64;

public class Main {
    private static final int DEFAULT_DATA_AMOUNT = 16;
    private static final int MAX_DATA_AMOUNT = 1024*1024;

    private final DigitalRandom rand = new DigitalRandom();

    private Main() {
    }

    private JsonRender intResponse() {
        return Jackson.json(new Response(true, rand.nextInt()));
    }

    private JsonRender dataResponse(String amount) {
        int amountInt = DEFAULT_DATA_AMOUNT;

        if (amount != null) {
            try {
                amountInt = Integer.parseInt(amount.trim());
            } catch (NumberFormatException ex) {
                return Jackson.json(new Response(false, null));
            }
        }
        if (amountInt > MAX_DATA_AMOUNT) {
            amountInt = MAX_DATA_AMOUNT;
        }
        final byte[] bytes = new byte[amountInt];
        rand.nextBytes(bytes);

        return Jackson.json(new Response(true, Base64.getEncoder().encodeToString(bytes)));
    }

    private void start() throws Exception {
        RatpackServer.start(server -> server.handlers(chain -> chain
                .get("int", ctx -> ctx.render(intResponse()))
                .get("data/:amount?", ctx -> ctx.render(dataResponse(ctx.getPathTokens().get("amount"))))
        ));
    }

    public static void main(String[] args) throws Exception {
        final Main server = new Main();
        server.start();
    }
}
