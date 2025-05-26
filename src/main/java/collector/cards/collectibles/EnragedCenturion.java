package collector.cards.collectibles;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;

@NoPools @NoCompendium @Deprecated
public class EnragedCenturion extends AbstractCollectibleCard {
    public final static String ID = makeID(EnragedCenturion.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , ,

    public EnragedCenturion() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        }
    }

    public void upp() {
//        upgradeMagicNumber(1);
        upgradeDamage(3);
    }
}