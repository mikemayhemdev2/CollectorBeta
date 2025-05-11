package collector.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import collector.CollectorChar;
import collector.cardmods.PyreMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.getModID;
import static collector.CollectorMod.makeCardPath;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.att;

public abstract class AbstractCollectorCard extends CustomCard {
    public String betaArtPath;

    protected final CardStrings cardStrings;

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradedsecondMagic;
    public boolean issecondMagicModified;

    public AbstractCollectorCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, CollectorChar.Enums.COLLECTOR);
    }

    public AbstractCollectorCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, "", getCorrectPlaceholderImage(type, cardID),
                cost, "", type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        rawDescription = cardStrings.DESCRIPTION;
        name = originalName = cardStrings.NAME;
        initializeTitle();
        initializeDescription();
    }


    @Override
    protected Texture getPortraitImage() {
        if (Settings.PLAYTESTER_ART_MODE || UnlockTracker.betaCardPref.getBoolean(this.cardID, false)) {
            if (this.textureImg == null) {
                return null;
            } else {
                if (betaArtPath != null) {
                    int endingIndex = betaArtPath.lastIndexOf(".");
                    String newPath = betaArtPath.substring(0, endingIndex) + "_p" + betaArtPath.substring(endingIndex);
                    System.out.println("Finding texture: " + newPath);

                    Texture portraitTexture;
                    try {
                        portraitTexture = ImageMaster.loadImage(newPath);
                    } catch (Exception var5) {
                        portraitTexture = null;
                    }

                    return portraitTexture;
                }
            }
        }
        return super.getPortraitImage();
    }

    public static String getCorrectPlaceholderImage(CardType type, String id) {
        String img = makeCardPath(id.replaceAll((getModID() + ":"), "") + ".png");
        if ((!Gdx.files.internal(img).exists()))
            switch (type) {
                case ATTACK:
                    return makeCardPath("Attack.png");
                case SKILL:
                    return makeCardPath("Skill.png");
                case POWER:
                    return makeCardPath("Power.png");
            }
        return img;
    }

    protected void uDesc() {
        if (cardStrings.UPGRADE_DESCRIPTION != null) {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
        }
    }

    public abstract void upp();

    // These shortcuts are specifically for cards. All other shortcuts that aren't specifically for cards can go in Wiz.
    protected void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    //Is this loss?
    protected void loss(AbstractMonster m, AbstractGameAction.AttackEffect fx, int amt) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, amt, DamageInfo.DamageType.HP_LOSS), fx));
    }

    protected void dmgTop(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    protected void allDmgTop(AbstractGameAction.AttackEffect fx) {
        att(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    protected void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    protected void isPyre() {
        CardModifierManager.addModifier(this, new PyreMod());
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedsecondMagic) {
            secondMagic = baseSecondMagic;
            issecondMagicModified = true;
        }

    }//https://www.youtube.com/watch?v=xMHJGd3wwZk - Context: Look at "Hurting (collector card)".

    protected boolean handHasKindling(){
        if (AbstractDungeon.player != null && AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !DFL.pl().hand.isEmpty()) {
            if (DFL.pl().hand.group.stream().anyMatch(c -> c.tags.contains(expansionContentMod.KINDLING))) {
                return true;
            }
        }
        return false;
    }

    public void applyToSelf(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    protected void upgradeSecondMagic(int amount) {
        baseSecondMagic += amount;
        secondMagic = baseSecondMagic;
        upgradedsecondMagic = true;
    }
}
