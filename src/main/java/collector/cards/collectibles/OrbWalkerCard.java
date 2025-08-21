package collector.cards.collectibles;

import collector.cards.Ember;
import collector.cards.OnPyreCard;
import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class OrbWalkerCard extends AbstractCollectibleCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(OrbWalkerCard.class.getSimpleName());
    // intellij stuff attack, enemy, common, 14, 4, , , 2, 1

    public OrbWalkerCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 13;
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Ember();
        isPyre();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);

        DFL.atb(new LaterAction(()->{
            if (kindling){
                makeInHand(new Ember(), magicNumber);
            }
        }));

    }

    private boolean kindling = false;
    @Override
    public void onPyred(AbstractCard card) {
        if (card.tags.contains(expansionContentMod.KINDLING)) {
            kindling = true;
        }
    }

    public void upp() {
        upgradeDamage(5);
       // upgradeMagicNumber(1);
    }
}