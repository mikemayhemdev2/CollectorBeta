package collector.cards.collectibles;

import collector.powers.collectioncards.GremlinGangPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sneckomod.SneckoMod;

import java.util.Objects;

import static collector.CollectorMod.GREMLINGANG;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;
import static utilityClasses.Wiz.*;

public class FatGremlinCard extends AbstractCollectibleCard {
    public final static String ID = makeID(FatGremlinCard.class.getSimpleName());
    // intellij stuff attack, enemy, common, 4, 1, , , 2, 1

    public FatGremlinCard() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(GREMLINGANG);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        if (!p.hasPower(GremlinGangPower.POWER_ID)) applyToSelf(new GremlinGangPower(this));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }


    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(GremlinGangPower.POWER_ID)) {
            GremlinGangPower power = (GremlinGangPower) AbstractDungeon.player.getPower(GremlinGangPower.POWER_ID);
            if (!Objects.equals(power.lastKnownGremlinCard.name, this.name))
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
            return;
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }
}