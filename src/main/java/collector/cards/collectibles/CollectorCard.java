package collector.cards.collectibles;

import collector.cards.OnPyreCard;
import collector.util.CollectorOrangeTextInterface;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;
import static utilityClasses.Wiz.*;

public class CollectorCard extends AbstractCollectibleCard implements OnPyreCard, CollectorOrangeTextInterface {
    // Ouroboros card name for easier search without changing class name
    public final static String ID = makeID(CollectorCard.class.getSimpleName());
    // intellij stuff skill, self_and_enemy, rare, , , , , 1, 1

    public CollectorCard() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 4;
        isPyre();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        atb(new AddTemporaryHPAction(p, p, secondMagic));

        DFL.atb(new LaterAction(()->{//If kindling, repeat.
            if (kindling){
                applyToEnemy(m, new WeakPower(m, magicNumber, false));
                applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
                atb(new AddTemporaryHPAction(p, p, secondMagic));
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
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}