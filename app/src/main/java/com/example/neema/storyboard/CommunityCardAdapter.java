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

public class CommunityCardAdapter extends RecyclerView.Adapter<CommunityCardAdapter.CommunityViewHolder>{
    private List<Card> communityCard;
    private Card card;

    public class CommunityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TODO ADD fields
        private TextView text;

        public CommunityViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            text = (TextView) view.findViewById(R.id.textView);

            //TODO GET THE FIELD FROM BY FIND VIEW BY ID
        }

        @Override
        public void onClick(View v){
            Intent intent = new Intent(v.getContext(), CommentsActivity.class);
            Card c = getAdapterPosition();
            intent.putExtra("cardId", card.getCardId());
            intent.putExtra("cardUserId", card.getUid());
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
        holder.text.setText(card.getTitle());

    }

    @Override
    public int getItemCount() {
        return communityCard.size();
    }
}

