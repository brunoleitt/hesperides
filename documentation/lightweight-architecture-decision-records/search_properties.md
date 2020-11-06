# Recherche de propriétés

Recherche de propriétés par nom et/ou valeur sur l'ensemble des applications.

## Règles spécifiques

* Ne tenir compte que des propriétés des modules déployés non archivés
* Cacher les valeurs des propriétés de type mot de passe pour les utilisateurs non habilités

## Nouveau endpoint

    /applications/search_properties?property_name={property_name}&property_value={property_value}
    
## Output

    [
      {
        property_name: String,
        property_value: String,
        application_name: String,
        platform_name: String,
        properties_path: String
      },
      ...
    ]
