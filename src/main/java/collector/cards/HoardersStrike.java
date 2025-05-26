package collector.cards;

import collector.CollectorCollection;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.att;

public class HoardersStrike extends AbstractCollectorCard {
    public final static String ID = makeID(HoardersStrike.class.getSimpleName());
    // intellij stuff attack, enemy, rare, 12, 5, , , , 

    public HoardersStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 6;
        tags.add(CardTags.STRIKE);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        //TODO: The scaling part
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}