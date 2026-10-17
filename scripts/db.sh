#!/usr/bin/env bash
# Uso: ./scripts/db.sh <up|migrate|reset|test|psql|down>
set -euo pipefail
cd "$(dirname "$0")/.."
[ -f .env ] || { echo "Crie .env a partir de .env.example"; exit 1; }

case "${1:-}" in
  up)      docker compose up -d --wait db && docker compose run --rm migrate ;;
  migrate) docker compose run --rm migrate ;;
  reset)   docker compose down -v && docker compose up -d --wait db && docker compose run --rm migrate ;;
  test)    docker compose exec -T db psql -U casajunta_owner -d casajunta -v ON_ERROR_STOP=1 -f /db/tests/smoke_test.sql ;;
  psql)    docker compose exec db psql -U casajunta_owner -d casajunta ;;
  down)    docker compose down ;;
  *)       echo "Uso: $0 <up|migrate|reset|test|psql|down>"; exit 1 ;;
esac
