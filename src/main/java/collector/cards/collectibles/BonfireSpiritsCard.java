package collector.cards.collectibles;

import collector.actions.GainReservesAction;
import collector.cards.OnPyreCard;
import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;
import static utilityClasses.Wiz.*;

public class BonfireSpiritsCard extends AbstractCollectibleCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(BonfireSpiritsCard.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 4, 2

    public BonfireSpiritsCard() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        isPyre();
        tags.add(CardTags.HEALING);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (pyredKindling) {
                    AbstractDungeon.player.increaseMaxHp(magicNumber, true);
                }
            }
        });
    }

    boolean pyredKindling = false;
    @Override
    public void onPyred(AbstractCard card) {
        if (card.tags.contains(expansionContentMod.KINDLING)) {
            pyredKindling = true;
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}