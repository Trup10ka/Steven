package me.trup10ka.shared.util

infix fun String.idOf(idType: IdType): String
{
    return when (idType)
    {
        IdType.EVENT -> this.split("-")[0]
        IdType.MEMBER -> this.split("-")[1]
    }
}