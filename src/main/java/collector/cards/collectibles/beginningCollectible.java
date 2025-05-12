package collector.cards.collectibles;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sneckomod.SneckoMod;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;

public class beginningCollectible  extends AbstractCollectibleCard {
    public final static String ID = makeID(beginningCollectible.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 5, 1, , , 3, 1

    public beginningCollectible() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 1;//It does damage so it isn't bad into a very early nob and that's it.
        baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(17);//Hello this upgrade is like an Easter-egg, the card does not exist past floor 6.
        upgradeMagicNumber(4);
    }
}
