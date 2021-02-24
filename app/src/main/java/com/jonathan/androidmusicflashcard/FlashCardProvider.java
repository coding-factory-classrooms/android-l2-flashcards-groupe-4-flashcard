package com.jonathan.androidmusicflashcard;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FlashCardProvider {


    private static final String TAG = "FlashCard";

    public ArrayList<FlashCard> loadFlashCards(Context context, Game.Theme theme) {
        ArrayList<FlashCard> flashCards = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String fileName = "";
        switch (theme) {
            case HipHop:
                fileName = "hip-hop.json";
                break;
            case Classic:
                fileName = "classic.json";
                break;
            case ELECTRO:
                fileName = "electro.json";
                break;
        }

        try {
            InputStream inputStream = context.getAssets().open(fileName);

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String content = new String(buffer);
            JSONArray array = new JSONArray(content);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                JSONArray jsonArray = obj.getJSONArray("answers");

                for (int j = 0; j < jsonArray.length(); j++) {
                    Log.i(TAG, "loadFlashCards: " + jsonArray.get(j));
                    answers.add((String) jsonArray.get(j));
                }

                FlashCard flashCard = new FlashCard(obj.getString("songName"), answers, (obj.getString("correctAnswer")));
                flashCards.add(flashCard);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return flashCards;

    }
}