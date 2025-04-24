package com.migrosone.mobiletestautomation.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.migrosone.mobiletestautomation.MainActivity
import com.migrosone.mobiletestautomation.constants.SignUpConstants
import com.migrosone.mobiletestautomation.pages.SignUpScreenPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val signUpPage by lazy { SignUpScreenPage(composeTestRule) }


    // Kullanıcının "Sign Up" ekranında E-mail, şifre ve isim bilgisi ile başarılı şekilde kayıt olduğu görülmelidir.
    @Test
    fun userShouldSignUpWithEmailPasswordAndNameInformation() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        signUpPage.assertHomePageFieldsVisible()
        Thread.sleep(1000) // Görsel takip için bekleme

        //2. "Sign Up" butonuna tıklandığı doğrulanır.
        signUpPage.clickOnSingUpButton()
        Thread.sleep(1000)

        //3. "Sign Up" ekranında başlık texti doğrulanır.
        signUpPage.assertSignUpTitleExist()
        Thread.sleep(1000)

        //4. Gerekli form alanlarının görünüp görünmediği doğrulanır.
        signUpPage.assertSignUpFieldsDisplayed()
        Thread.sleep(1000)

        //5. Geçerli Email, Şifre ve İsim bilgisi girilir.
        signUpPage.fillSignUpForm(
            SignUpConstants.VALID_EMAIL,
            SignUpConstants.VALID_PASSWORD,
            SignUpConstants.VALID_NAME
        )
        Thread.sleep(1000)

        //6. Formun gönderildiği doğrulanır. ("Sign Up" butonuna tıklanır.)
        signUpPage.clickOnSignUpButtonAllNodesWithText()
        Thread.sleep(1000)

        //7. "Membership successful!" başarı mesajı için beklenir.
        signUpPage.waitSuccessMembershipMessage()
        Thread.sleep(1000)

        //8. "Membership successful!" başarı mesajının görüntülendiği doğrulanır.
        signUpPage.assertSuccessMembershipMessage()
        Thread.sleep(1000)

        //9. "Sign In Now" butonuna tıklandığı doğrulanır.
        signUpPage.clickOnSignInNowButton()
        Thread.sleep(2000)
    }

    // Kullanıcının "Sign Up" ekranında herhangi bir bilgi girişi yapmadığında kayıt olmadığı görülmelidir.
    @Test
    fun userShouldNotSignUpWithoutRequiredField() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        signUpPage.assertHomePageFieldsVisible()
        Thread.sleep(1000)

        //2. "Sign Up" butonuna tıklandığı doğrulanır.
        signUpPage.clickOnSingUpButton()
        Thread.sleep(1000)

        //3. "Sign Up" ekranında başlık texti doğrulanır.
        signUpPage.assertSignUpTitleExist()
        Thread.sleep(1000)

        //4. Gerekli form alanlarının görünüp görünmediği doğrulanır.
        signUpPage.assertSignUpFieldsDisplayed()
        Thread.sleep(1000)

        //5. Herhangi bir alan doldurulmadan kayıt olmaya çalışılır.
        signUpPage.clickOnSignUpButtonAllNodesWithText()
        Thread.sleep(1000)

        //6. "Membership successful!" başarı mesajının görüntülenmemesi doğrulanır.
        signUpPage.assertNotEqualsSuccessMembershipMessage()
        Thread.sleep(2000)
    }

    // Kullanıcının "Sign Up" ekranında "sadece isim" bilgisi girişi yaptığında kayıt olmadığı görülmelidir.
    @Test
    fun userShouldNotSignUpWithOnlyUseName() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        signUpPage.assertHomePageFieldsVisible()
        Thread.sleep(1000)

        //2. "Sign Up" butonuna tıklandığı doğrulanır.
        signUpPage.clickOnSingUpButton()
        Thread.sleep(1000)

        //3. "Sign Up" ekranında başlık texti doğrulanır.
        signUpPage.assertSignUpTitleExist()
        Thread.sleep(1000)

        //4. Gerekli form alanlarının görünüp görünmediği doğrulanır.
        signUpPage.assertSignUpFieldsDisplayed()
        Thread.sleep(1000)

        //5. Sadece isim bilgisi girilir.
        signUpPage.fillSignUpForm("", "", SignUpConstants.VALID_NAME)
        Thread.sleep(1000)

        //6. "Sign Up" butonuna tıklanır. (Form gönderilir.)
        signUpPage.clickOnSignUpButtonAllNodesWithText()
        Thread.sleep(1000)

        //7. "Membership successful!" başarı mesajının görüntülenmemesi doğrulanır.
        signUpPage.assertNotEqualsSuccessMembershipMessage()
        Thread.sleep(2000)
    }

    // Kullanıcının "Sign Up" ekranında "sadece şifre" bilgisi girişi yaptığında kayıt olmadığı görülmelidir.
    @Test
    fun userShouldNotSignUpWithOnlyUsePassword() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        signUpPage.assertHomePageFieldsVisible()
        Thread.sleep(1000)

        //2. "Sign Up" butonuna tıklandığı doğrulanır.
        signUpPage.clickOnSingUpButton()
        Thread.sleep(1000)

        //3. "Sign Up" ekranında başlık texti doğrulanır.
        signUpPage.assertSignUpTitleExist()
        Thread.sleep(1000)

        //4. Gerekli form alanlarının görünüp görünmediği doğrulanır.
        signUpPage.assertSignUpFieldsDisplayed()
        Thread.sleep(1000)

        //5. Sadece şifre bilgisi girilir.
        signUpPage.fillSignUpForm("", SignUpConstants.VALID_PASSWORD, "")
        Thread.sleep(1000)

        //6. "Sign Up" butonuna tıklanır. (Form gönderilir.)
        signUpPage.clickOnSignUpButtonAllNodesWithText()
        Thread.sleep(1000)

        //7. "Membership successful!" başarı mesajının görüntülenmemesi doğrulanır.
        signUpPage.assertNotEqualsSuccessMembershipMessage()
        Thread.sleep(2000)
    }

    // Kullanıcının "Sign Up" ekranında "sadece email" bilgisi girişi yaptığında kayıt olmadığı görülmelidir.
    @Test
    fun userShouldNotSignUpWithOnlyUseEmail() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        signUpPage.assertHomePageFieldsVisible()
        Thread.sleep(1000)

        //2. "Sign Up" butonuna tıklandığı doğrulanır.
        signUpPage.clickOnSingUpButton()
        Thread.sleep(1000)

        //3. "Sign Up" ekranında başlık texti doğrulanır.
        signUpPage.assertSignUpTitleExist()
        Thread.sleep(1000)

        //4. Gerekli form alanlarının görünüp görünmediği doğrulanır.
        signUpPage.assertSignUpFieldsDisplayed()
        Thread.sleep(1000)

        //5. Sadece email bilgisi girilir.
        signUpPage.fillSignUpForm(SignUpConstants.VALID_EMAIL, "", "")
        Thread.sleep(1000)

        //6. "Sign Up" butonuna tıklanır. (Form gönderilir.)
        signUpPage.clickOnSignUpButtonAllNodesWithText()
        Thread.sleep(1000)

        //7. "Membership successful!" başarı mesajının görüntülenmemesi doğrulanır.
        signUpPage.assertNotEqualsSuccessMembershipMessage()
        Thread.sleep(2000)
    }

    // Kullanıcının "Sign Up" ekranında "geçersiz email, geçerli şifre, geçerli isim" bilgisi girişi yaptığında kayıt olmadığı görülmelidir.
    @Test
    fun userShouldNotSignUpWithNotValidEmail() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        signUpPage.assertHomePageFieldsVisible()
        Thread.sleep(1000)

        //2. "Sign Up" butonuna tıklandığı doğrulanır.
        signUpPage.clickOnSingUpButton()
        Thread.sleep(1000)

        //3. "Sign Up" ekranında başlık texti doğrulanır.
        signUpPage.assertSignUpTitleExist()
        Thread.sleep(1000)

        //4. Gerekli form alanlarının görünüp görünmediği doğrulanır.
        signUpPage.assertSignUpFieldsDisplayed()
        Thread.sleep(1000)

        //5. Geçersiz email, geçerli şifre ve geçerli isim bilgisi girilir.
        signUpPage.fillSignUpForm(
            SignUpConstants.INVALID_EMAIL,
            SignUpConstants.VALID_PASSWORD,
            SignUpConstants.VALID_NAME
        )
        Thread.sleep(1000)

        //6. "Sign Up" butonuna tıklanır. (Form gönderilir.)
        signUpPage.clickOnSignUpButtonAllNodesWithText()
        Thread.sleep(1000)

        //7. "Membership successful!" başarı mesajının görüntülenmemesi doğrulanır.
        signUpPage.assertNotEqualsSuccessMembershipMessage()
        Thread.sleep(2000)
    }

}

