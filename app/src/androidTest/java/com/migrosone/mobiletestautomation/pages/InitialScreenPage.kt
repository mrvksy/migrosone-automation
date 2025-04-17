package com.migrosone.mobiletestautomation.pages

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import com.migrosone.mobiletestautomation.constants.InitialScreenConstants
import com.migrosone.mobiletestautomation.constants.SignUpConstants

class InitialScreenPage(private val rule: ComposeTestRule) {
    fun assertCheckMigrosLogoDisplayed() {
        rule.onNodeWithContentDescription(InitialScreenConstants.LOGO_DESC, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun assertCheckSignInButtonDisplayed() {
        rule.onNodeWithText(InitialScreenConstants.SIGN_IN_TEXT).assertIsDisplayed()
    }

    fun assertSignInButtonHasClickAction() {
        rule.onNodeWithText(InitialScreenConstants.SIGN_IN_TEXT).assertHasClickAction()
    }

    fun assertCheckSignUpButtonDisplayed() {
        rule.onNodeWithText(InitialScreenConstants.SIGN_UP_TEXT).assertIsDisplayed()
    }

    fun assertSignUpButtonHasClickAction() {
        rule.onNodeWithText(InitialScreenConstants.SIGN_UP_TEXT).assertHasClickAction()
    }

    fun clickOnSignInButton() {
        rule.onAllNodesWithText(InitialScreenConstants.SIGN_IN_TEXT, useUnmergedTree = true)[0]
            .performTouchInput { click() }
    }

    fun checkEmailFieldDisplayed() {
        rule.onNodeWithText(InitialScreenConstants.EMAIL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun checkPasswordFieldDisplayed() {
        rule.onNodeWithText(InitialScreenConstants.PASSWORD, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun checkSignInButtonDisplayed() {
        rule
            .onAllNodesWithText(InitialScreenConstants.SIGN_IN_TEXT, useUnmergedTree = true)[0]
            .assertIsDisplayed()
    }

    fun assertCheckSignInFormFields() {
        checkEmailFieldDisplayed()
        checkPasswordFieldDisplayed()
        checkSignInButtonDisplayed()
    }

    fun waitEmailFieldForSignInForm() {
        rule.waitUntil(timeoutMillis = 5000) {
            rule.onAllNodesWithText(InitialScreenConstants.EMAIL)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
    }

    fun clickOnSingUpButton() {
        rule.onNodeWithText(SignUpConstants.SIGN_UP_TEXT, useUnmergedTree = true)
            .performClick()
    }


    fun checkEmailFieldDisplayedForSingUpPage() {
        rule.onNodeWithText(SignUpConstants.EMAIL_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun checkPasswordFieldDisplayedForSingUpPage() {
        rule.onNodeWithText(SignUpConstants.PASSWORD_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun checkNameFieldDisplayedForSingUpPage() {
        rule.onNodeWithText(SignUpConstants.NAME_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun assertSignUpFieldsDisplayed() {
        checkEmailFieldDisplayedForSingUpPage()
        checkPasswordFieldDisplayedForSingUpPage()
        checkNameFieldDisplayedForSingUpPage()
    }

}