package com.example.neema.storyboard;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import java.util.List;

public class CommunityCardAdapter extends RecyclerView.Adapter<CommunityCardAdapter.CommunityViewHolder> {
    private List<Card> communityCard;
    private Card card;

    public class CommunityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TODO ADD fields
        private TextView title, cardUsername, description, type;
        

        public CommunityViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            cardUsername = view.findViewById(R.id.cardUsername);
            description = view.findViewById(R.id.description);
            type = view.findViewById(R.id.type);

            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), CommentsActivity.class);
            intent.putExtra("cardId", communityCard.get(getAdapterPosition()).getCardId());
            intent.putExtra("cardUserId", communityCard.get(getAdapterPosition()).getUid());
            v.getContext().startActivity(intent);
        }
    }

    public CommunityCardAdapter(List<Card> communityCard) {
        this.communityCard = communityCard;
    }
    @Override
    public CommunityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards, parent, false);

        return new CommunityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommunityViewHolder holder, int position) {
        card = communityCard.get(position);
        if (card.getCardType() == CardType.FREEWRITE) {
            holder.title.setText(card.getTitle());
            holder.description.setText(card.getText());
        }
        else if (card.getCardType() == CardType.PROMPT) {
            holder.title.setText(card.getText());
            holder.description.setText("");
        }
        else if (card.getCardType() == CardType.WEEKLY) {
            holder.title.setText("Weekly Challenge 1");
            holder.description.setText(card.getText());
        }
        holder.cardUsername.setText(card.getUsername());
        holder.type.setText(cardTypeToString(card.getCardType()));
    }

    private String cardTypeToString(CardType cardType) {
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

    @Override
    public int getItemCount() {
        return communityCard.size();
    }
}

