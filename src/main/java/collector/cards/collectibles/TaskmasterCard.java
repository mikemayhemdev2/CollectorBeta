package collector.cards.collectibles;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sneckomod.SneckoMod;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class TaskmasterCard extends AbstractCollectibleCard {
    public final static String ID = makeID(TaskmasterCard.class.getSimpleName());

    public TaskmasterCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        applyToEnemy(m, new DoomPower(m, magicNumber));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        applyToSelf(new StrengthPower(p, magicNumber));
        atb(new MakeTempCardInDrawPileAction(new Wound(), secondMagic, false, true));

    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}