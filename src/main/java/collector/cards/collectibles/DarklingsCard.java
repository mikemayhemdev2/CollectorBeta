package collector.cards.collectibles;

import collector.cards.OnPyreCard;
import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;
import static collector.CollectorMod.makeID;

public class DarklingsCard extends AbstractCollectibleCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(DarklingsCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , , 

    public DarklingsCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        isPyre();
        baseDamage = 7;
        baseMagicNumber = magicNumber = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }

        DFL.atb(new LaterAction(()->{
            if (kindling){//Copy!
                DFL.atb(new MakeTempCardInDrawPileAction(this.makeStatEquivalentCopy(), 1, true, true));
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
        upgradeDamage(1);
        upgradeMagicNumber(1);
//        this.selfRetain = true;
//        uDesc();
    }
}