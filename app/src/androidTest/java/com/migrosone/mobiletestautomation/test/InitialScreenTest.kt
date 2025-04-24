package com.migrosone.mobiletestautomation.test


import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.migrosone.mobiletestautomation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.migrosone.mobiletestautomation.pages.InitialScreenPage

@RunWith(AndroidJUnit4::class)
class InitialScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val initialScreenPage by lazy { InitialScreenPage(composeTestRule) }

    // Anasayfa'da "Sign In"  butonuna tıklanabildiği ve "Sign In" ekranın açıldığı görülmelidir.
    @Test
    fun signInAndSignUpButtonsAreVisibleAndOpenSignInPage() {
        // 1. "Migros logosunun" görüntülendiği doğrulanır.
        initialScreenPage.assertCheckMigrosLogoDisplayed()
        Thread.sleep(1000)

        // 2. "Sign In" butonunun görüntülendiği doğrulanır.
        initialScreenPage.assertCheckSignInButtonDisplayed()
        Thread.sleep(1000)

        // 3. "Sign In" butonunun tıklanabilir olduğu doğrulanır.
        initialScreenPage.assertSignInButtonHasClickAction()
        Thread.sleep(1000)

        // 4. "Sign Up" butonu görüntülendiği doğrulanır.
        initialScreenPage.assertCheckSignUpButtonDisplayed()
        Thread.sleep(1000)

        // 5. "Sign Up" butonunun tıklanabilir olduğu doğrulanır.
        initialScreenPage.assertSignUpButtonHasClickAction()
        Thread.sleep(1000)

        // 6. "Sign In" butonuna tıklanır
        initialScreenPage.clickOnSignInButton()
        Thread.sleep(1000)

        // 7. "Sign In" ekranı yüklenene kadar beklenir
        initialScreenPage.waitEmailFieldForSignInForm()
        Thread.sleep(1000)

        // 8. "Sign In" ekranının açıldığı doğrulanır.
        initialScreenPage.assertCheckSignInFormFields()
        Thread.sleep(3000)
    }

    // Anasayfa'da "Sign Up"  butonuna tıklanabildiği ve "Sign Up" ekranın açıldığı görülmelidir.
    @Test
    fun signInAndSignUpButtonsAreVisibleAndOpenSignUpPage() {
        // 1. "Migros logosunun" görüntülendiği doğrulanır.
        initialScreenPage.assertCheckMigrosLogoDisplayed()
        Thread.sleep(1000)

        // 2. "Sign In" butonunun görüntülendiği doğrulanır.
        initialScreenPage.assertCheckSignInButtonDisplayed()
        Thread.sleep(1000)

        // 3. "Sign In" butonunun tıklanabilir olduğu doğrulanır.
        initialScreenPage.assertSignInButtonHasClickAction()
        Thread.sleep(1000)

        // 4. "Sign Up" butonunun görüntülendiği doğrulanır.
        initialScreenPage.assertCheckSignUpButtonDisplayed()
        Thread.sleep(1000)

        // 5. "Sign Up" butonunun tıklanabilir olduğu doğrulanır.
        initialScreenPage.assertSignUpButtonHasClickAction()
        Thread.sleep(1000)

        // 6. "Sign Up" butonuna tıklanır.
        initialScreenPage.clickOnSingUpButton()
        Thread.sleep(1000)

        // 7. "Sign Up" ekranındaki alanlar doğrulanır.
        initialScreenPage.assertSignUpFieldsDisplayed()
        Thread.sleep(3000)
    }
}