package com.example.neema.storyboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    public List<Card> cards;
    public class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView title, text, privacy, type;

        public CardViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            text = (TextView) view.findViewById(R.id.description);
            privacy = (TextView) view.findViewById(R.id.privacy);
            type = (TextView) view.findViewById(R.id.type);

        }
    }
    public CardAdapter(List<Card> cards) {
        this.cards = cards;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.freewrite_card,parent,false);
        return new CardViewHolder(itemView);
    }
    @Override
    public int getItemCount() {
        return cards.size();
    }
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {

        Card card = cards.get(position);
        holder.title.setText(card.getTitle());
        holder.text.setText(card.getText());
        holder.privacy.setText(boolToString(card.isPublic()));
        holder.type.setText(typeToString(card.getCardType()));
    }

    public String boolToString(boolean isPublic) {
        if (isPublic)
            return "Public";
        return "Private";
    }

    public String typeToString (CardType cardType) {
        switch (cardType) {
            case FREEWRITE:
                return "Freewrite";
            case PROMPT:
                return "Prompt";
            case WEEKLY:
                return "Weekly";
        }
        return "Error";
    }
}
