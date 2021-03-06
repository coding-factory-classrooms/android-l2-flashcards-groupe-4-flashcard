package com.jonathan.androidmusicflashcard;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> implements View.OnClickListener {

    private List<FlashCard> flashCards;

    public QuestionAdapter(List<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FlashCard flashCard = flashCards.get(position);
        holder.answers.setText(flashCard.getAnswers().toString());
        holder.theme.setText(flashCard.getTheme().toString());

        holder.itemView.setTag(flashCard);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return flashCards.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rootItem:
                Context context = v.getContext();
                ArrayList<FlashCard> cards = new ArrayList<>();
                FlashCard flashCard = (FlashCard) v.getTag();
                cards.add(flashCard);
                FlashCardGame flashCardGame = new FlashCardGame(flashCard.getTheme(),cards);
                Intent questionActivityIntent = new Intent(context, QuestionActivity.class);
                questionActivityIntent.putExtra("game", flashCardGame);
                context.startActivity(questionActivityIntent);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        final ImageView sound;
        final TextView answers;
        final TextView theme;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sound = itemView.findViewById(R.id.soundImageView);
            answers = itemView.findViewById(R.id.answersTextView);
            theme = itemView.findViewById(R.id.themeTextView);
        }
    }
}

