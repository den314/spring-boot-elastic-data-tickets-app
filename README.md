# Ticketing Application
Stores tickets created by worker in elastic search.
Each ticket has desired date of resolution, priority and assignee.

## Description
Uses Spring Data Elastic Java API 2.x for operating on ticket documents.
Runs with different Spring Profiles:
- elastic-repository - uses ElasticsearchRepository for operating on documents
- elastic-template - uses ElasticsearchTemplate
- dev : saves sample data into elastic on application startup

### Technology used
- spring boot 2.0.x
- elasticsearch 6.2.x
