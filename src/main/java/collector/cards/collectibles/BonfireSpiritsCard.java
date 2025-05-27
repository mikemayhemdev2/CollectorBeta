package collector.cards.collectibles;

import collector.cards.OnPyreCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;
import static utilityClasses.Wiz.*;

public class BonfireSpiritsCard extends AbstractCollectibleCard implements OnPyreCard {
    public final static String ID = makeID(BonfireSpiritsCard.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 4, 2

    public BonfireSpiritsCard() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        isPyre();
        tags.add(CardTags.HEALING);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    private boolean wasRare = false;

    public void use(AbstractPlayer p, AbstractMonster m) {
      //  atb(new HealAction(p, p, magicNumber));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (wasRare) {
                    att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractDungeon.player.increaseMaxHp(magicNumber, true);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onPyred(AbstractCard card) {
        if (card.rarity == CardRarity.RARE) {
            wasRare = true;
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}