package collector.cards.collectibles;

import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class SsserpentCard extends AbstractCollectibleCard {
    public final static String ID = makeID(SsserpentCard.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 20, 6, , , , 

    public SsserpentCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 7;
        cardsToPreview = new Doubt();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }//Now with one more "s" for each update!

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        applyToEnemy(m, new DoomPower(m, magicNumber));
        atb(new MakeTempCardInDiscardAction(new Doubt(), 1));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}