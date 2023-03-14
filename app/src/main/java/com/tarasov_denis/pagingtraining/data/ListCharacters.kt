package com.tarasov_denis.pagingtraining.data

data class ListCharacters (
    val info: Info = Info(),
    val results: List<Character> // = listOf<CharacterResponse>()
) {
    data class Info(
        val count : Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )
}
